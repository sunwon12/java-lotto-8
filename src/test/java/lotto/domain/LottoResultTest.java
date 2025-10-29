package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void 당첨_결과를_집계한다() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.addResult(LottoRank.FIFTH);
        lottoResult.addResult(LottoRank.FIFTH);
        lottoResult.addResult(LottoRank.FOURTH);

        assertThat(lottoResult.getCount(LottoRank.FIFTH)).isEqualTo(2);
        assertThat(lottoResult.getCount(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(lottoResult.getCount(LottoRank.THIRD)).isEqualTo(0);
    }

    @Test
    void 총_상금을_계산한다() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.addResult(LottoRank.FIFTH);
        lottoResult.addResult(LottoRank.FOURTH);

        long totalPrizeMoney = lottoResult.getTotalPrizeMoney();
        assertThat(totalPrizeMoney).isEqualTo(55_000);
    }

    @Test
    void 낙첨은_결과에_추가되지_않는다() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.addResult(LottoRank.NONE);

        assertThat(lottoResult.getCount(LottoRank.NONE)).isEqualTo(0);
    }
}
