package com.enoca.payload.response.abstractresponse;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseAbstractResponse {

    private LocalDateTime creationDate;

}
