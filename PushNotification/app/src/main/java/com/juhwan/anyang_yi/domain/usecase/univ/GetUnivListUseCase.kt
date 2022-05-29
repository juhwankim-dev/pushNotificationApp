package com.juhwan.anyang_yi.domain.usecase.univ

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetUnivListUseCase @Inject constructor(
    private val univRepository: UnivRepository
) {
    operator fun invoke(): Result<List<Univ>> = univRepository.getUnivNoticeList()
}