package model.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.dto.Review;
import model.dao.ReviewDAO;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;

	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ReviewManager getInstance() {
		return reviewMan;
	}
	
	/* 메인페이지에 출력할 랜덤 리뷰 리스트 반환 */
	public List<Review> getRandomReviews() throws SQLException {
		List<Review> reviewList = reviewDAO.findReviewList();
		List<Review> randomReviews = null;

		if (reviewList != null) {
			Collections.shuffle(reviewList);			
			randomReviews = reviewList.subList(0, 3);
			
			for (Review review : randomReviews)
				review = addReviewProperties(review);
		}
		
		return randomReviews;
	}

	/* 특정 돌보미의 후기 리스트 반환 */
	public List<Review> findReviewListOfSitter(String sitterId) throws SQLException {
		List<Review> reviews = reviewDAO.findReviewListOfSitter(sitterId);
		if (reviews != null)
			for (Review review : reviews)
				review = addReviewProperties(review);

		return reviews;
	}
	
	/* 리뷰 추가 */
	public boolean add(Review review, String memberId) throws SQLException {
		int count = reviewDAO.add(review);
		if (count == 0) return false;
		
		List<String> images = review.getImages();
		for (String imgSrc : images) {
			imgSrc = "/upload/" + imgSrc;
			count = reviewDAO.addAttachment(imgSrc, memberId);
			if (count == 0) return false;
		}
		return true;
	}
	
	//전체 리뷰 반환
	public List<Review> findReviewList() throws SQLException {
		List<Review> reviewList = reviewDAO.findReviewList();
	
		if (reviewList != null ) {
			for (Review review : reviewList) {
				String[] address = review.getCareInfo().getSitter().getSitter().getAddress().split(" ");
				String city = null;
				for (int j = 0; j < address.length; j++) {
					if (address[j].matches("(.*)로")) {
						city = address[j].substring(0, address[j].length() - 1);
					}
				}
				review.getCareInfo().getSitter().getSitter().setAddress(city);
				
				List<String> imgList = reviewDAO.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
				review.setImages(imgList);
			}
		}
		return reviewList;
	}

	/* 리뷰의 속성 중 주소, 이미지  추가 */
	public Review addReviewProperties(Review review) throws SQLException {
		String[] address = review.getCareInfo().getSitter().getSitter().getAddress().split(" ");
		String city = null;
		for (int j = 0; j < address.length; j++) {
			if (address[j].matches("(.*)로")) {
				city = address[j].substring(0, address[j].length() - 1);
			}
		}
		review.getCareInfo().getSitter().getSitter().setAddress(city);
		List<String> images = reviewDAO.findReviewAttachments(review.getCareInfo().getCompanion().getId(), review.getCareInfo().getId());
		review.setImages(images);
		
		return review;
	}
}
