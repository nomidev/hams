package com.huneth.hams.admin.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1192926121L;

    public static final QMenu menu = new QMenu("menu");

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> lastUpdateDate = createDateTime("lastUpdateDate", java.time.LocalDateTime.class);

    public final StringPath menuActiveCode = createString("menuActiveCode");

    public final StringPath menuDesc = createString("menuDesc");

    public final StringPath menuIcon = createString("menuIcon");

    public final NumberPath<Integer> menuLevelNo = createNumber("menuLevelNo", Integer.class);

    public final StringPath menuName = createString("menuName");

    public final StringPath menuRole = createString("menuRole");

    public final StringPath menuUrl = createString("menuUrl");

    public final StringPath parentId = createString("parentId");

    public final NumberPath<Integer> sortOrderNo = createNumber("sortOrderNo", Integer.class);

    public final BooleanPath useFlag = createBoolean("useFlag");

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

