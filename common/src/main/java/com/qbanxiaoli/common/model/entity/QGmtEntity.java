package com.qbanxiaoli.common.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGmtEntity is a Querydsl query type for GmtEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QGmtEntity extends EntityPathBase<GmtEntity> {

    private static final long serialVersionUID = 495882215L;

    public static final QGmtEntity gmtEntity = new QGmtEntity("gmtEntity");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> gmtCreated = createNumber("gmtCreated", Long.class);

    public final NumberPath<Long> gmtModified = createNumber("gmtModified", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QGmtEntity(String variable) {
        super(GmtEntity.class, forVariable(variable));
    }

    public QGmtEntity(Path<? extends GmtEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGmtEntity(PathMetadata metadata) {
        super(GmtEntity.class, metadata);
    }

}

