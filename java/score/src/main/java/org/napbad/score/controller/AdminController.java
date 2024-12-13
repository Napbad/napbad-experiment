package org.napbad.score.controller;

import org.napbad.score.model.Admin;
import org.napbad.score.model.DataSource;

public class AdminController {

    private final DataSource dataSource;

    public AdminController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean login(int id, String password)
    {
        Admin res = dataSource.getAdmins().stream().filter(
                admin -> admin.getId() == (id) &&
                        admin.getPasswd().equals(password)
        ).findFirst().orElse(null);

        return res != null;
    }
}
