package org.song.global.enties;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEnitry is a Querydsl query type for BaseEnitry
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEnitry extends EntityPathBase<BaseEnitry> {

    private static final long serialVersionUID = 590695410L;

    public static final QBaseEnitry baseEnitry = new QBaseEnitry("baseEnitry");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QBaseEnitry(String variable) {
        super(BaseEnitry.class, forVariable(variable));
    }

    public QBaseEnitry(Path<? extends BaseEnitry> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEnitry(PathMetadata metadata) {
        super(BaseEnitry.class, metadata);
    }

}

