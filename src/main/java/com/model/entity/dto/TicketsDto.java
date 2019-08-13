package com.model.entity.dto;

import com.model.entity.Session;
import com.model.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TicketsDto {
    @NotEmpty
    List<Integer> places;
    @NotNull
    Session session;
    @NotNull
    String user;
}
