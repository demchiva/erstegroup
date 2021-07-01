package cz.erstegroup.example.demo.controller.combine_products;

import cz.erstegroup.example.demo.model.combined.CombinedResults;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URISyntaxException;


/**
 * The base for combine the transparent accounts with products controller.
 */
@Validated
interface CombineProductsBaseController {
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns page of Erste transparent accounts combined with products",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CombinedResults.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Internal error",
                            content = @Content
                    )
            }
    )
    @Operation(
            summary = "Get the combined amount of product with account relates to current balance",
            description = "Returns page of Erste transparent accounts combined with products"
    )
    @GetMapping("/combinedResults")
    String getTransparentAccountsWithProducts() throws URISyntaxException;

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns page of Erste transparent accounts combined with products amount of witch more then given amount",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CombinedResults.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Internal error",
                            content = @Content
                    )
            }
    )
    @Operation(
            summary = "Get the combined amount of product greater then given amount with account relates to current balance",
            description = "Returns page of Erste transparent accounts combined with products amount of witch more them given amount"
    )
    @GetMapping("/combinedProductsMoreThanAmount")
    String getTransparentAccountsWithProductsMoreThanGivenAmount(@RequestParam(name = "amount") @Min(0) @Max(100_000) final Double amount) throws URISyntaxException;
}
