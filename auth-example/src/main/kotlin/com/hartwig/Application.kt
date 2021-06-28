package com.hartwig

import io.micronaut.openapi.annotation.OpenAPISecurity
import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
	info = Info(
		title = "auth-example",
		version = "0.0"
	)
)
@OpenAPISecurity
object Api {
}
fun main(args: Array<String>) {
	build()
		.args(*args)
		.packages("com.hartwig")
		.start()
}