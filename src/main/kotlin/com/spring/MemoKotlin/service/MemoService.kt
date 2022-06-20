package com.spring.MemoKotlin.service

import com.spring.MemoKotlin.domain.Memo
import com.spring.MemoKotlin.repository.MemoRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemoService {

    @Autowired
    private lateinit var memoRepository: MemoRepository

    @Transactional
    fun save(memo: Memo): Long? {
        memoRepository.save(memo)
        return memo.id
    }

    @Transactional
    fun update(id: Long, title: String, content: String) {
        val memo = memoRepository.findOne(id)
        memoRepository.update(memo, title, content)
    }

    @Transactional
    fun remove(id: Long) {
        val memo = memoRepository.findOne(id)
        memoRepository.remove(memo)
    }

    fun findOne(id: Long): Memo {
        return memoRepository.findOne(id)
    }

    fun findAll(): List<Memo> {
        return memoRepository.findAll()
    }

    fun findSearch(keyWord: String?):List<Memo> {
        return if (keyWord == null || keyWord.isEmpty()) memoRepository.findAll()
        else memoRepository.findSearch(keyWord)
    }
}