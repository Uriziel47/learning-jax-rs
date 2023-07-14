package model;

import adapter.UUIDAdapter;
import org.approvaltests.Approvals;
import org.approvaltests.ReporterFactory;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.intellij.IntelliJUltimateReporter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import serialize.ChildSerializer;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ModelToJsonTest {

    @Test
    public void topLevelToJson_no_children() {
        TopLevel toTest = TopLevel.of(
                UUID.fromString("23c4cacc-3ccb-4c1d-9cf7-f1a4211b0028"),
                "topElement",
                "I am top",
                Status.OK
        );

        Jsonb jsonb = provideJsonb();
        String toTestAsJson = jsonb.toJson(toTest);

        Approvals.verify(toTestAsJson, provideApprovalOptions());
    }

    @Test
    public void topLevelToJson_with_children_wo_top() {
        TopLevel toTest = TopLevel.of(
                UUID.fromString("55113115-6fba-4a31-b075-8124393727c6"),
                "topElement_with_children",
                "I am top and have children,",
                Status.OK);
        toTest.getChildren().addAll(
                Arrays.asList(
                        Child.of(
                                UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"),
                                "Child 1",
                                Status.OK
                        ),
                        Child.of(
                                UUID.fromString("fe01a888-2584-4605-abbe-78469a8dde8b"),
                                "Child 2",
                                Status.DELETED
                        ),
                        Child.of(
                                UUID.fromString("66ff4ca7-73bd-49bc-a6b6-72e42cc0258c"),
                                "Child 3",
                                Status.UNUSED
                        )
                )
        );

        Jsonb jsonb = provideJsonb();
        String toTestAsJson = jsonb.toJson(toTest);

        Approvals.verify(toTestAsJson, provideApprovalOptions());
    }

    @Test
    public void topLevelToJson_with_children() {
        TopLevel toTest = TopLevel.of(
                UUID.fromString("55113115-6fba-4a31-b075-8124393727c6"),
                "topElement_with_children",
                "I am top and have children,",
                Status.OK
        );
        List<Child> children = Arrays.asList(
                Child.builder()
                        .top(toTest)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("Child 1")
                        .status(Status.OK)
                        .build(),
                Child.builder()
                        .top(toTest)
                        .id(UUID.fromString("fe01a888-2584-4605-abbe-78469a8dde8b"))
                        .name("Child 2")
                        .status(Status.DELETED)
                        .build(),
                Child.builder()
                        .top(toTest)
                        .id(UUID.fromString("66ff4ca7-73bd-49bc-a6b6-72e42cc0258c"))
                        .name("Child 3")
                        .status(Status.UNUSED)
                        .build()
        );
        toTest.getChildren().addAll(children);

        Jsonb jsonb = provideJsonb();
        String toTestAsJson = jsonb.toJson(toTest);

        Approvals.verify(toTestAsJson, provideApprovalOptions());
    }

    @Test
    public void topLevelToJson_with_children_and_subChildren() {
        TopLevel toTest = TopLevel.of(
                UUID.fromString("55113115-6fba-4a31-b075-8124393727c6"),
                "topElement_with_children",
                "I am top and have children,",
                Status.OK
        );
        {
            var child = Child.builder()
                    .top(toTest)
                    .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                    .name("Child 1")
                    .status(Status.OK)
                    .build();
            toTest.getChildren().add(child);
            {
                var subChild = Child.builder()
                        .top(toTest)
                        .parent(child)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("SubChild 1.1")
                        .status(Status.OK)
                        .build();
                child.getSubElements().add(subChild);
            }
            {
                var subChild = Child.builder()
                        .top(toTest)
                        .parent(child)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("SubChild 1.2")
                        .status(Status.OK)
                        .build();
                child.getSubElements().add(subChild);
            }
        }
        {
            var child = Child.builder()
                    .top(toTest)
                    .id(UUID.fromString("fe01a888-2584-4605-abbe-78469a8dde8b"))
                    .name("Child 2")
                    .status(Status.DELETED)
                    .build();
            toTest.getChildren().add(child);
            {
                var subChild = Child.builder()
                        .top(toTest)
                        .parent(child)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("SubChild 2.1")
                        .status(Status.OK)
                        .build();
                child.getSubElements().add(subChild);
            }
            {
                var subChild = Child.builder()
                        .top(toTest)
                        .parent(child)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("SubChild 2.2")
                        .status(Status.OK)
                        .build();
                child.getSubElements().add(subChild);
            }
            {
                var subChild = Child.builder()
                        .top(toTest)
                        .parent(child)
                        .id(UUID.fromString("40da7c39-6034-428d-9b7c-29c56e88893a"))
                        .name("SubChild 2.3")
                        .status(Status.OK)
                        .build();
                child.getSubElements().add(subChild);
            }
        }
        {
            var child = Child.builder()
                    .top(toTest)
                    .id(UUID.fromString("66ff4ca7-73bd-49bc-a6b6-72e42cc0258c"))
                    .name("Child 3")
                    .status(Status.UNUSED)
                    .build();
            toTest.getChildren().add(child);
        }

        Jsonb jsonb = provideJsonb();
        String toTestAsJson = jsonb.toJson(toTest);

        Approvals.verify(toTestAsJson, provideApprovalOptions());
    }

    @Test
    public void childToJson_no_parent_no_children() {
        Child toTest = Child.of(
                UUID.fromString("0d9c2f10-e203-467e-ae1e-937a6fdde3ee"),
                "child",
                Status.OK
        );

        Jsonb jsonb = provideJsonb();
        String toTestAsJson = jsonb.toJson(toTest);

        Approvals.verify(toTestAsJson, provideApprovalOptions());
    }

    @Test
    public void generateUUID() {
        UUID next = UUID.randomUUID();
        System.out.println(next);
    }

    public Options provideApprovalOptions() {
        Options result = new Options()
                .forFile()
                .withExtension(".json");

        return result;
    }

    public Jsonb provideJsonb() {
        JsonbConfig config = buildJsonbConfig();
        return JsonbBuilder.newBuilder()
                .withConfig(config)
                .build();
    }

    public JsonbConfig buildJsonbConfig() {
        return new JsonbConfig()
                .withFormatting(true)
                .withAdapters(
                        new UUIDAdapter()
                )
                .withSerializers(
                        new ChildSerializer()
                )
                ;

    }
}
