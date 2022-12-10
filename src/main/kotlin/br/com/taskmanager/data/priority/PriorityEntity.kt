package br.com.taskmanager.data.priority

import br.com.taskmanager.data.abstractEntity.AbstractEntity
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_priority")
data class PriorityEntity(
        var description: String,
        var key: String,
        var index: Int,
) : AbstractEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PriorityEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}