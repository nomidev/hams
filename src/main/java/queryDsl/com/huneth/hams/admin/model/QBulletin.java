package com.huneth.hams.admin.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBulletin is a Querydsl query type for Bulletin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBulletin extends EntityPathBase<Bulletin> {

    private static final long serialVersionUID = -1885304079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBulletin bulletin = new QBulletin("bulletin");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final BooleanPath useFlag = createBoolean("useFlag");

    public final com.huneth.hams.member.model.QUser user;

    public QBulletin(String variable) {
        this(Bulletin.class, forVariable(variable), INITS);
    }

    public QBulletin(Path<? extends Bulletin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBulletin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBulletin(PathMetadata metadata, PathInits inits) {
        this(Bulletin.class, metadata, inits);
    }

    public QBulletin(Class<? extends Bulletin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.huneth.hams.member.model.QUser(forProperty("user")) : null;
    }

}

