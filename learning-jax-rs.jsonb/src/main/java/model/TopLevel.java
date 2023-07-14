package model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@Builder
public class TopLevel {
    @NonNull private UUID id;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private Status status;
    private List<Child> children;
}
