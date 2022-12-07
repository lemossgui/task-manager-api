package br.com.taskmanager.controller.exception

class NotFoundException(msg: String? = "A entidade não foi encontrada") : RuntimeException(msg)