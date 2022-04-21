package kz.sabyrzhan.hrleavemanagement.infra.peristence;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
public class EntityPrePersistListener implements BeforeConvertCallback<BaseDomainEntity> {
    @Override
    public Publisher<BaseDomainEntity> onBeforeConvert(BaseDomainEntity entity, SqlIdentifier table) {
        entity.setLastModifiedDate(Instant.now());
        if (entity.getDateCreated() == null) {
            entity.setDateCreated(Instant.now());
        }

        return Mono.just(entity);
    }
}
