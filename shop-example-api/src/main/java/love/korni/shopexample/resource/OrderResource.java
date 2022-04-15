package love.korni.shopexample.resource;

import love.korni.shopexample.dto.CreateOrderDto;
import love.korni.shopexample.dto.ErrorResponse;
import love.korni.shopexample.dto.OrderDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * OrderResource
 *
 * @author Sergei_Konilov
 */
@Tag(name = "Order Resource", description = "Order API")
@SecurityRequirement(name = "basicSecurity")
@RequestMapping("/api/v1/order")
public interface OrderResource {

    @Operation(summary = "Create new order", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping
    OrderDto create(@RequestBody CreateOrderDto createOrderDto);

    @Operation(summary = "Get order by id", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/{id}")
    OrderDto getOrder(@PathVariable Long id);

    @Operation(summary = "Get order for user by username", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/user/{username}")
    List<OrderDto> getOrders(@PathVariable String username);

    @Operation(summary = "Get list of orders", description = "Security: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping
    List<OrderDto> getOrders();

    @Operation(summary = "Confirm order", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/{id}/confirm")
    OrderDto confirmOrder(@PathVariable Long id);

    @Operation(summary = "Update order", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PutMapping
    OrderDto update(@RequestBody OrderDto orderDto);

    @Operation(summary = "Remove order by id", description = "Security: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> remove(@PathVariable Long id);

}
