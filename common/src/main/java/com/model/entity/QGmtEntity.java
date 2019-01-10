package com.model.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


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

    //inherited
    public final StringPath uuid = _super.uuid;

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

