package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor(staticName = "of")
@Builder(toBuilder = true)
public class TopLevel {
    @NonNull private UUID id;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private Status status;
    @Getter private final List<Child> children = new ArrayList<>();
}
