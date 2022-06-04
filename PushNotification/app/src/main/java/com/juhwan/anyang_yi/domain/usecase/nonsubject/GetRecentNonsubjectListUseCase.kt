package com.juhwan.anyang_yi.domain.usecase.nonsubject

import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.domain.repository.NonsubjectRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetRecentNonsubjectListUseCase @Inject constructor(
    private val nonsubjectRepository: NonsubjectRepository
) {
    operator fun invoke(): Result<List<Nonsubject>> = nonsubjectRepository.getRecentNonsubjectNoticeList()
}