package br.com.taskmanager.data.abstractEntity

import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0
    open var isExcluded: Boolean = false
}