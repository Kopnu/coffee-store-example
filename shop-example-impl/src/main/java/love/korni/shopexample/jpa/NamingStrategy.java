/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * NamingStrategy
 *
 * @author Sergei_Kornilov
 */
public class NamingStrategy extends SpringPhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String tableName = name.getText();
        return super.toPhysicalTableName(Identifier.toIdentifier(tableName), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalSequenceName(Identifier.toIdentifier(name.getText()), jdbcEnvironment);
    }

}
