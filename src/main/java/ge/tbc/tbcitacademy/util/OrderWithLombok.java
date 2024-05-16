package ge.tbc.tbcitacademy.util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

public class OrderWithLombok {
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"id", "petId", "quantity", "shipDate", "status", "complete"})
    public static class Order {
        public int id;
        public int petId;
        public int quantity;
        public String status;
        public boolean complete;
    }

}
