package com.project.review.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.review.enums.ReplyType;
import com.project.review.model.ReplyRequestDto;
import com.project.review.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class ReplyController {

	private final ReplyService replyService;

	@GetMapping("/")
	public String view(Model model) {
		if (Objects.isNull(model.getAttribute("replyRequestDto"))) {
			model.addAttribute("replyRequestDto", new ReplyRequestDto());
		}

		return "reviewReply";
	}

	@PostMapping("/review")
	public String createReviewReply(@ModelAttribute ReplyRequestDto replyRequestDto, RedirectAttributes redirectAttributes) {
		ReplyType replyType = replyRequestDto.getReplyType();
		String review = replyRequestDto.getReview();

		String reviewReply = replyService.createReviewReply(replyType, review);

		redirectAttributes.addFlashAttribute("replyRequestDto", replyRequestDto);
		redirectAttributes.addFlashAttribute("reviewReply", reviewReply);

		return "redirect:/";
	}
}
