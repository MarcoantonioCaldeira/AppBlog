package com.blog.service.mapper
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class EntityConverter(private val mapper: ModelMapper) {

    fun <O, I> parseObject(input: I, outputClass: Class<O>): O {
        return mapper.map(input, outputClass)
    }

    fun <O, I> parseListObjects(input: List<I>, outputClass: Class<O>): List<O> {
        return input.map { mapper.map(it, outputClass) }
    }
}