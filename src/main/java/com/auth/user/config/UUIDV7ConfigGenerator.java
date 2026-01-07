package com.auth.user.config;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.github.f4b6a3.uuid.UuidCreator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UUIDV7ConfigGenerator implements IdentifierGenerator  {
    
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // TODO Auto-generated method stub

        UUID uuidv7 = UuidCreator.getTimeOrderedEpoch();
    
        log.info("Generated UUIDv7: {}", uuidv7.toString());
        
        return uuidv7.toString();
    }
}