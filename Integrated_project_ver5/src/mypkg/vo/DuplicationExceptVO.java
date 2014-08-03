package mypkg.vo;

import java.util.HashSet;
import java.util.List;

public class DuplicationExceptVO {
	private HashSet<Integer> exceptDuplicationWebtoons;
	private List<MatchingVO> matchingInfo;
	
	public DuplicationExceptVO(HashSet<Integer> exceptDuplicationWebtoons, List<MatchingVO> matchingInfo) {
		this.exceptDuplicationWebtoons = exceptDuplicationWebtoons;
		this.matchingInfo = matchingInfo;
	}

	public HashSet<Integer> getExceptDuplicationWebtoons() {
		return exceptDuplicationWebtoons;
	}

	public void setExceptDuplicationWebtoons(
			HashSet<Integer> exceptDuplicationWebtoons) {
		this.exceptDuplicationWebtoons = exceptDuplicationWebtoons;
	}

	public List<MatchingVO> getMatchingInfo() {
		return matchingInfo;
	}

	public void setMatchingInfo(List<MatchingVO> matchingInfo) {
		this.matchingInfo = matchingInfo;
	}
}
