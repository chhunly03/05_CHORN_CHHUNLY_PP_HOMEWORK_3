package com.khrd.homework_spring_data_jpa_2.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTbRequest {
    private Integer quantity;
    private Integer productId;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Order_tb toOrderTb() {
        return new Order_tb(this.quantity, this.productId);
    }
}
