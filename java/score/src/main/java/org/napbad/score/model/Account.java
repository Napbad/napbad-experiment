package org.napbad.score.model;

import lombok.Data;

@Data
public abstract class Account {
    private int id;
    private String passwd;
}
