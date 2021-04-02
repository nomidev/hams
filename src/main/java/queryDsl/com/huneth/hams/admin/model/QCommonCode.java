package com.huneth.hams.admin.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonCode is a Querydsl query type for CommonCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommonCode extends EntityPathBase<CommonCode> {

    private static final long serialVersionUID = 1372226882L;

    public static final QCommonCode commonCode = new QCommonCode("commonCode");

    public final StringPath attribute1 = createString("attribute1");

    public final StringPath attribute2 = createString("attribute2");

    public final StringPath attribute3 = createString("attribute3");

    public final StringPath attribute4 = createString("attribute4");

    public final StringPath attribute5 = createString("attribute5");

    public final StringPath code = createString("code");

    public final StringPath codeDesc = createString("codeDesc");

    public final StringPath codeName = createString("codeName");

    public final StringPath codeType = createString("codeType");

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath parentCode = createString("parentCode");

    public final NumberPath<Integer> sortNo = createNumber("sortNo", Integer.class);

    public final EnumPath<com.huneth.hams.common.commonEnum.YnFlag> useFlag = createEnum("useFlag", com.huneth.hams.common.commonEnum.YnFlag.class);

    public QCommonCode(String variable) {
        super(CommonCode.class, forVariable(variable));
    }

    public QCommonCode(Path<? extends CommonCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonCode(PathMetadata metadata) {
        super(CommonCode.class, metadata);
    }

}

