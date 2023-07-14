package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@Builder(toBuilder = true)
public class Child {

    private TopLevel top;
    @NonNull private UUID id;
    @NonNull private String name;
    @NonNull private Status status;
    private Child parent;
    @Getter private final List<Child> subElements = new ArrayList<>();

}
