package com.pierandrei.RHSystem.scheduled;

import com.pierandrei.RHSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final AdminService adminService;


    @Scheduled(cron = "0 59 23 * * *") // Executa às 23:59 todos os dias
    public void removeLeave() {
        System.out.println("Removendo funcionários da licença!");
        this.adminService.removeLeave(LocalDate.now());
    }
}
