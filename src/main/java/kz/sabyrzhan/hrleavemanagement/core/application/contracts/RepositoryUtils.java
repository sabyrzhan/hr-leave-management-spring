package kz.sabyrzhan.hrleavemanagement.core.application.contracts;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class RepositoryUtils {
    public static Map<Integer, LeaveType> leaveTypesToMap(List<LeaveType> list) {
        return list.stream().collect(Collectors.toMap(LeaveType::getId, identity()));
    }
}
