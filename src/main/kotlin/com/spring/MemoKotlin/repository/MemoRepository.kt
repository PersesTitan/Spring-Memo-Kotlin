package com.spring.MemoKotlin.repository

import com.spring.MemoKotlin.domain.Memo
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemoRepository {

    @PersistenceContext
    private lateinit var em: EntityManager

    fun save(memo: Memo) {
        em.persist(memo)
    }

    fun remove(memo: Memo) {
        em.remove(memo)
    }

    fun update(memo: Memo, title: String, content: String) {
        memo.title = title
        memo.content = content
    }

    fun findOne(id: Long): Memo {
        return em.find(Memo::class.java, id)
    }

    fun findAll(): List<Memo> {
        return em.createQuery("SELECT M FROM Memo AS M", Memo::class.java)
            .resultList
    }

    fun findSearch(keyWord: String): List<Memo> {
        return em.createQuery("SELECT M FROM Memo AS M WHERE M.title LIKE :keyWord", Memo::class.java)
                .setParameter("keyWord", "%$keyWord%")
                .resultList
    }
}