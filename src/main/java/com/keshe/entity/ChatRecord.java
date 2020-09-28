package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRecord {
    @NotNull
    private String comUserId;
    @NotNull
    private String toUserId;
    @NotNull
    private String message;
    private Date recordTime;
}
