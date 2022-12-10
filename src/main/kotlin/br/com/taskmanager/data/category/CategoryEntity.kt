package br.com.taskmanager.data.category

import br.com.taskmanager.data.abstractEntity.AbstractEntity
import br.com.taskmanager.data.user.UserEntity
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "tb_category")
data class CategoryEntity(
        var description: String,
        var colorKey: String,

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var user: UserEntity,
) : AbstractEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CategoryEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}