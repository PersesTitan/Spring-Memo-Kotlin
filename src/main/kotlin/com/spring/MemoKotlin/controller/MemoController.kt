package com.spring.MemoKotlin.controller

import com.spring.MemoKotlin.domain.Memo
import com.spring.MemoKotlin.domain.MemoDTO
import com.spring.MemoKotlin.service.MemoService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpServletRequest

@Controller
@RequiredArgsConstructor
class MemoController {

    @Autowired
    private lateinit var memoService: MemoService

    @GetMapping("/memos")
    fun memoList(model: Model, httpServiceRequest: HttpServletRequest): String {
        val search: String? = httpServiceRequest.getParameter("search")
        val memos: List<Memo> = memoService.findSearch(search)
        model.addAttribute("searchParam", !(search == null || search.isBlank()))
        model.addAttribute("search", search)
        model.addAttribute("memos", memos)
        return "memo_list"
    }

    @GetMapping("/memo")
    fun createFrom(): String {
        return "new_memo"
    }

    @PostMapping("/memo")
    fun createMemo(memo: Memo, redirectAttributes: RedirectAttributes): String {
        val id: Long? = memoService.save(memo)
        redirectAttributes.addAttribute("id", id)
        redirectAttributes.addAttribute("create", true)
        return "redirect:/memo/{id}"
    }

    @GetMapping("/memo/{id}")
    fun memo(@PathVariable id: Long, model: Model): String {
        val memo: Memo = memoService.findOne(id)
        model.addAttribute("id", id)
        model.addAttribute("memo", memo)
        return "item/memo"
    }

    //메모 편집 페이지
    @GetMapping("/memo/{id}/edit")
    fun editFrom(@PathVariable id: Long, model: Model): String {
        val memo: Memo = memoService.findOne(id)
        model.addAttribute("id", id)
        model.addAttribute("memo", memo)
        return "edit_memo"
    }

    @PostMapping("/memo/{id}/edit")
    fun editMemo(@PathVariable id: Long, memoDTO: MemoDTO): String {
        memoService.update(id, memoDTO.title, memoDTO.content)
        return "redirect:/memo/{id}"
    }

    @PostMapping("/memo/{id}/remove")
    fun remove(@PathVariable id: Long): String {
        memoService.remove(id)
        return "redirect:/memos"
    }
}