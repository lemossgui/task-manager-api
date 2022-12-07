package br.com.taskmanager.service.priority

import br.com.taskmanager.data.priority.PriorityEntity
import org.springframework.stereotype.Service

@Service
interface PriorityService {
    fun findAll(): List<PriorityEntity>
}