package example.com.dto;

import java.math.BigDecimal;

public record UpdateMovie (String title, BigDecimal price, String genre) {
}
