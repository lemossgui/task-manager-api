package br.com.taskmanager.data.user

import br.com.taskmanager.data.abstractEntity.AbstractEntity
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
data class UserEntity(
        var name: String,
        var email: String,
        var password: String,
        var notificationIsEnable: Boolean,
        var image: String? = null,
) : AbstractEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}