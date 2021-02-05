package com.juhwan.anyang_yi.fragments.calendar

class ScheduleData {
    fun requestSchedule(month: Int): MutableList<Schedule> {
        when(month){
            3 -> return march()
            4 -> return april()
            5 -> return may()
            6 -> return june()
            7 -> return july()
            8 -> return august()
            9 -> return september()
            10 -> return october()
            11 -> return november()
            12 -> return december()
        }

        return mutableListOf(
            Schedule(
                "해당 월에는 일정이 없습니다.",
                ""
            )
        )
    }

    private fun march(): MutableList<Schedule> {
        return mutableListOf(
            Schedule("삼일절", "03.01(월)"),
            Schedule(
                "1학기 개강",
                "03.02(화)"
            ),
            Schedule(
                "2차 수강정정 기간",
                "03.02(화)~03.08(월)"
            ),
            Schedule(
                "2차 일반휴학 신청 기간",
                "03.02(화)~03.05(금)"
            ),
            Schedule(
                "전액 2차 등록 기간",
                "03.08(월)~03.12(금)"
            ),
            Schedule(
                "조기졸업 신청 기간",
                "03.08(월)~03.09(화)"
            ),
            Schedule(
                "수강철회 기간",
                "03.22(월)~03.23(화)"
            ),
            Schedule(
                "수업주수 1/4선",
                "03.26(금)"
            )
        )
    }

    private fun april(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "수시고사 기간",
                "04.20(화)~04.26(월)"
            ),
            Schedule(
                "수업주수 2/4선",
                "04.22(목)"
            ),
            Schedule(
                "중간강의평가 및 수시고사 성적 확인",
                "04.30(금)~05.04(화)"
            )
        )
    }

    private fun may(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "어린이날(보강일: 06.09(수))",
                "05.05(수)"
            ),
            Schedule(
                "수료자 졸업 신청 기간",
                "05.10(월)~05.11(화)"
            ),
            Schedule(
                "체육대회",
                "05.12(수)∼05.13(목)"
            ),
            Schedule(
                "부처님 오신 날(보강일: 06.10.(목))",
                "05.19(수)"
            ),
            Schedule(
                "수업주수 3/4선",
                "05.21(금)"
            )
        )
    }

    private fun june(): MutableList<Schedule> {
        return mutableListOf(
            Schedule("현충일", "06.06(일)"),
            Schedule(
                "보강주(어린이날: 06.09(수), 부처님 오신 날: 06.10(목))",
                "06.08(화)∼06.14(월)"
            ),
            Schedule(
                "기말고사 기간",
                "06.15(화)∼06.21(월)"
            ),
            Schedule(
                "성적입력 기간",
                "06.15(화)~06.28(월)"
            ),
            Schedule(
                "1학기 종강",
                "06.21(월)"
            ),
            Schedule(
                "하계 계절수업 개강",
                "06.22(화)"
            ),
            Schedule(
                "강의평가, 성적확인 및 정정 기간",
                "06.29(화)~07.02(금)"
            )
        )
    }

    private fun july(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "하계 계절수업 종강",
                "07.12(월)"
            ),
            Schedule(
                "재입학 신청 기간",
                "07.12(월)~07.16(금)"
            ),
            Schedule(
                "휴학 및 복학 신청 기간",
                "07.19(월)~07.30(금)"
            ),
            Schedule(
                "부·복·연계·자기설계전공 및 전과 신청",
                "07.19(월)~07.23(금)"
            ),
            Schedule(
                "졸업연기 신청 기간",
                "07.26(월)~07.27(화)"
            )
        )
    }

    private fun august(): MutableList<Schedule> {
        return mutableListOf(
            Schedule("광복절", "08.15(일)"),
            Schedule(
                "대학원 후기 학위수여식",
                "08.20(금)"
            ),
            Schedule(
                "2학기 수강신청 기간",
                "08.23(월)~08.26(목)"
            ),
            Schedule(
                "1차 수강정정 기간",
                "08.27(금)"
            ),
            Schedule(
                "전액 1차 등록기간",
                "08.23(월)~08.27(금)"
            )
        )
    }

    private fun september(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "2학기 개강",
                "09.01(수)"
            ),
            Schedule(
                "축제",
                "09.28(화)~09.30(목)"
            )
        )
    }

    private fun october(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "수업주수 1/4선",
                "10.01(금)"
            ),
            Schedule("한글날", "10.09(토)"),
            Schedule(
                "수시고사 기간",
                "10.20(수)~10.26(화)"
            ),
            Schedule(
                "수업주수 2/4선",
                "10.29(목)"
            )
        )
    }

    private fun november(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "중간강의평가 및 수시고사 성적 확인",
                "11.01(월)~11.03(수)"
            ),
            Schedule(
                "수료자 졸업 신청 기간",
                "11.08(월)~11.09(화)"
            ),
            Schedule(
                "수업주수 3/4선",
                "11.24(화)"
            )
        )
    }

    private fun december(): MutableList<Schedule> {
        return mutableListOf(
            Schedule(
                "보강주(추석연휴: 12.08(수), 12.13(월), 12.14(화), 개교기념일: 12.10(금))",
                "12.08(수)∼12.14(화)"
            ),
            Schedule(
                "기말고사 기간",
                "12.15(수)∼12.21(화)"
            ),
            Schedule(
                "성적입력 기간",
                "12.15(수)∼12.21(화)"
            ),
            Schedule(
                "2학기 종강",
                "12.21(화)"
            ),
            Schedule(
                "동계 계절수업 개강",
                "12.22(수)"
            ),
            Schedule(
                "강의평가, 성적확인 및 정정 기간",
                "12.29(수)~01.03(월)"
            )
        )
    }
}