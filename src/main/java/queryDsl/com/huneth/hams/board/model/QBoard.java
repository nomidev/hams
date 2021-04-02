package com.huneth.hams.board.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 116749043L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath attachedFile = createString("attachedFile");

    public final com.huneth.hams.admin.model.QBulletin bulletin;

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final com.huneth.hams.member.model.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bulletin = inits.isInitialized("bulletin") ? new com.huneth.hams.admin.model.QBulletin(forProperty("bulletin"), inits.get("bulletin")) : null;
        this.user = inits.isInitialized("user") ? new com.huneth.hams.member.model.QUser(forProperty("user")) : null;
    }

}

