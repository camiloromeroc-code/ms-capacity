package com.bootcamps.ms_technologies.infrastructure.adapter.in.web;

import com.bootcamps.ms_technologies.domain.model.Technology;
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
public class TechnologyRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/technologies",
                    method = RequestMethod.GET,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "getAll",
                    operation = @Operation(
                            operationId = "getAllTechnologies",
                            summary = "Obtener todas las tecnologías",
                            description = "Devuelve el listado completo de tecnologías registradas en el sistema",
                            tags = {"Technologies"},
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Lista de tecnologías obtenida exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Technology.class)
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies/{id}",
                    method = RequestMethod.GET,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "getById",
                    operation = @Operation(
                            operationId = "getTechnologyById",
                            summary = "Obtener tecnología por ID",
                            description = "Busca una tecnología específica por su identificador único",
                            tags = {"Technologies"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la tecnología",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Tecnología encontrada",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Technology.class)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Tecnología no encontrada"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "create",
                    operation = @Operation(
                            operationId = "createTechnology",
                            summary = "Crear nueva tecnología",
                            description = "Crea una nueva tecnología en el sistema con los datos proporcionados",
                            tags = {"Technologies"},
                            requestBody = @RequestBody(
                                    description = "Datos de la tecnología a crear",
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Technology.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Tecnología creada exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Technology.class)
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
                    path = "/api/v1/technologies/{id}",
                    method = RequestMethod.PUT,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "updateTechnology",
                            summary = "Actualizar tecnología existente",
                            description = "Actualiza completamente los datos de una tecnología existente",
                            tags = {"Technologies"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la tecnología a actualizar",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            requestBody = @RequestBody(
                                    description = "Datos actualizados de la tecnología",
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Technology.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Tecnología actualizada exitosamente",
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Technology.class)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Tecnología no encontrada"
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Datos de entrada inválidos"
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies/{id}",
                    method = RequestMethod.DELETE,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(
                            operationId = "deleteTechnology",
                            summary = "Eliminar tecnología",
                            description = "Elimina permanentemente una tecnología del sistema",
                            tags = {"Technologies"},
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            description = "Identificador único de la tecnología a eliminar",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            schema = @Schema(type = "integer", format = "int64", example = "1")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "204",
                                            description = "Tecnología eliminada exitosamente"
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Tecnología no encontrada"
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> technologyRoutes(TechnologyHandler handler) {
        return route(GET("/api/v1/technologies"), handler::getAll)
                .andRoute(GET("/api/v1/technologies/{id}"), handler::getById)
                .andRoute(POST("/api/v1/technologies"), handler::create)
                .andRoute(PUT("/api/v1/technologies/{id}"), handler::update)
                .andRoute(DELETE("/api/v1/technologies/{id}"), handler::delete);
    }
}