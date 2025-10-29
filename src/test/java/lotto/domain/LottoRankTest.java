package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    void 일치하는_번호가_6개면_1등이다() {
        LottoRank rank = LottoRank.valueOf(6, false);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000);
    }

    @Test
    void 일치하는_번호가_5개이고_보너스가_일치하면_2등이다() {
        LottoRank rank = LottoRank.valueOf(5, true);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000);
    }

    @Test
    void 일치하는_번호가_5개이고_보너스가_불일치하면_3등이다() {
        LottoRank rank = LottoRank.valueOf(5, false);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
        assertThat(rank.getPrizeMoney()).isEqualTo(1_500_000);
    }

    @Test
    void 일치하는_번호가_4개면_4등이다() {
        LottoRank rank = LottoRank.valueOf(4, false);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(50_000);
    }

    @Test
    void 일치하는_번호가_3개면_5등이다() {
        LottoRank rank = LottoRank.valueOf(3, false);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(5_000);
    }

    @Test
    void 일치하는_번호가_2개_이하면_낙첨이다() {
        assertThat(LottoRank.valueOf(2, false)).isEqualTo(LottoRank.NONE);
        assertThat(LottoRank.valueOf(1, false)).isEqualTo(LottoRank.NONE);
        assertThat(LottoRank.valueOf(0, false)).isEqualTo(LottoRank.NONE);
    }

    @Test
    void 낙첨은_당첨이_아니다() {
        assertThat(LottoRank.NONE.isWinning()).isFalse();
    }

    @Test
    void 당첨_등수는_당첨이다() {
        assertThat(LottoRank.FIRST.isWinning()).isTrue();
        assertThat(LottoRank.SECOND.isWinning()).isTrue();
        assertThat(LottoRank.THIRD.isWinning()).isTrue();
        assertThat(LottoRank.FOURTH.isWinning()).isTrue();
        assertThat(LottoRank.FIFTH.isWinning()).isTrue();
    }
}
