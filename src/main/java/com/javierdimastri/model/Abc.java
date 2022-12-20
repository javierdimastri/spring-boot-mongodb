package com.javierdimastri.model;

import com.google.common.base.Objects;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "abc")
public class Abc {
    @Id
    private ObjectId id;

    @NotBlank
    private String name;
    private String description;

    public Abc(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Abc abc = (Abc) object;
        return Objects.equal(id, abc.id) && Objects.equal(name, abc.name) && Objects.equal(description, abc.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description);
    }
}
