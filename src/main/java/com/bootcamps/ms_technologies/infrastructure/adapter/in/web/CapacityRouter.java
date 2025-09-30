package com.bootcamps.ms_technologies.infrastructure.adapter.in.web;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CapacityRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/capacities",
                    method = RequestMethod.GET,
                    beanClass = CapacityHandler.class,
                    beanMethod = "getAll",
                    operation = @Operation(
                            operationId = "getAllCapacities",
                            summary = "Obtener todas las capacidades",
                            description = "Devuelve el listado completo de capacidades registradas en el sistema",
                            tags = {"Capacity"},
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Lista de capacidades obtenida exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Capacity.class)
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capacities/{id}",
                    method = RequestMethod.GET,
                    beanClass = CapacityHandler.class,
                    beanMethod = "getById",
                    operation = @Operation(
                            operationId = "getCapacityById",
                            summary = "Obtener capacidad por ID",
                            description = "Busca una capacidad específica por su identificador único",
                            tags = {"Capacity"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la capacidad",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Capacidad encontrada",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Capacity.class)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Capacidad no encontrada"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capacities",
                    method = RequestMethod.POST,
                    beanClass = CapacityHandler.class,
                    beanMethod = "create",
                    operation = @Operation(
                            operationId = "createCapacity",
                            summary = "Crear nueva capacidad",
                            description = "Crea una nueva capacidad en el sistema con los datos proporcionados",
                            tags = {"Capacity"},
                            requestBody = @RequestBody(
                                    description = "Datos de la capacidad a crear",
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Capacity.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Capacidad creada exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Capacity.class)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Datos de entrada inválidos"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capacities/{id}",
                    method = RequestMethod.PUT,
                    beanClass = CapacityHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "updateCapacity",
                            summary = "Actualizar capacidad existente",
                            description = "Actualiza completamente los datos de una capacidad existente",
                            tags = {"Capacity"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la capacidad a actualizar",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            requestBody = @RequestBody(
                                    description = "Datos actualizados de la capacidad",
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Capacity.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Capacidad actualizada exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Capacity.class)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Capacidad no encontrada"
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Datos de entrada inválidos"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capacities/{id}",
                    method = RequestMethod.DELETE,
                    beanClass = CapacityHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(
                            operationId = "deleteCapacity",
                            summary = "Eliminar capacidad",
                            description = "Elimina permanentemente una capacidad del sistema",
                            tags = {"Capacity"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la capacidad a eliminar",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "204",
                                            description = "Capacidad eliminada exitosamente"
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Capacidad no encontrada"
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> capacityRoutes(CapacityHandler handler) {
        return route(GET("/api/v1/capacities"), handler::getAll)
                .andRoute(GET("/api/v1/capacities/{id}"), handler::getById)
                .andRoute(POST("/api/v1/capacities"), handler::create)
                .andRoute(PUT("/api/v1/capacities/{id}"), handler::update)
                .andRoute(DELETE("/api/v1/capacities/{id}"), handler::delete);
    }
}
