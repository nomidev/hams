package com.huneth.hams.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 557672009L;

    public static final QRole role = new QRole("role");

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final EnumPath<RoleType> roleCode = createEnum("roleCode", RoleType.class);

    public final StringPath roleName = createString("roleName");

    public final ListPath<UserRole, QUserRole> userRoleList = this.<UserRole, QUserRole>createList("userRoleList", UserRole.class, QUserRole.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

