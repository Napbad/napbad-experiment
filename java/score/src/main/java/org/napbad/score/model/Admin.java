package org.napbad.score.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends Account {
    int id = 1;
    String passwd = "admin";

    public Admin(
            int id,
            String passwd
    ) {
        this.id = id;
        this.passwd = passwd;
    }
}
