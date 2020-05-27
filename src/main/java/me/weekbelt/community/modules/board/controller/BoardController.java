package me.weekbelt.community.modules.board.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.community.modules.account.Account;
import me.weekbelt.community.modules.account.CurrentAccount;
import me.weekbelt.community.modules.board.form.BoardWriteForm;
import me.weekbelt.community.modules.board.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String boards(@CurrentAccount Account account, @PageableDefault Pageable pageable,
                         @RequestParam String boardType, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("boards", boardService.findBoardList(boardType, pageable));
        model.addAttribute("boardType", boardType);
        return "board/" + boardType;
    }

    @GetMapping("/boards/{id}")
    public String readBoard(@CurrentAccount Account account, @PathVariable Long id,
                            Model model) {
        model.addAttribute("account", account);
        model.addAttribute("boardReadForm", boardService.findBoardReadFormById(id));
        return "board/readForm";
    }
//
//    @GetMapping("/boards/{id}/update")
//    public

    @GetMapping("/new-board")
    public String createNewBoard(@CurrentAccount Account account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("board", new BoardWriteForm());
        return "board/creationForm";
    }

//    @PostMapping("/new-board")
//    public String createNewBoardSubmit(@CurrentAccount Account account, @Valid BoardRequestForm boardRequestForm,
//                                       Errors errors, Model model) {
//
//    }
}