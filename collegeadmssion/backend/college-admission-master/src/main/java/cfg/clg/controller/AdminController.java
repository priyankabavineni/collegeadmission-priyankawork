package cfg.clg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cfg.clg.dto.AdminStudentDTO;
import cfg.clg.dto.ApplicationActionDto;
import cfg.clg.dto.ApplicationResponseDto;
import cfg.clg.service.AdminService;
import cfg.clg.controller.ResponseData;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired 
    private AdminService adminService;

    @GetMapping("/applications/{status}")
    public ResponseData<List<ApplicationResponseDto>> getByStatus(@PathVariable("status") String status) {
        ResponseData<List<ApplicationResponseDto>> resp = new ResponseData<>();
        try {
            List<ApplicationResponseDto> list = adminService.getApplicationsByStatus(status);
            resp.setStatus("success");
            resp.setData(list);
        } catch (Exception e) {
            resp.setStatus("failed");
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @GetMapping("/applications")
    public ResponseData<List<ApplicationResponseDto>> getAllApplications() {
        ResponseData<List<ApplicationResponseDto>> resp = new ResponseData<>();
        try {
            List<ApplicationResponseDto> list = adminService.getAllApplications();
            resp.setStatus("success");
            resp.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus("failed");
            resp.setMessage(e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return resp;
    }

    @PostMapping("/action")
    public ResponseData<ApplicationResponseDto> actOnApplication(@RequestBody ApplicationActionDto dto) {
        ResponseData<ApplicationResponseDto> resp = new ResponseData<>();
        try {
            ApplicationResponseDto updated = adminService.actOnApplication(dto);
            resp.setStatus("success");
            resp.setData(updated);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus("failed");
            resp.setMessage(e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return resp;
    }

    @GetMapping("/applications/program/{program}/department/{department}")
    public ResponseData<List<ApplicationResponseDto>> getApplicationsByProgramAndDepartment(
            @PathVariable String program,
            @PathVariable String department) {

        ResponseData<List<ApplicationResponseDto>> resp = new ResponseData<>();
        try {
            List<ApplicationResponseDto> list = adminService.getApplicationsByProgramAndDepartment(program, department);
            if (list.isEmpty()) {
                resp.setStatus("failed");
                resp.setMessage("No applications found for " + program + " - " + department);
            } else {
                resp.setStatus("success");
                resp.setData(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus("failed");
            resp.setMessage(e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return resp;
    }

    @GetMapping("/student/email/{email}")
    public ResponseData<AdminStudentDTO> getStudentByEmail(@PathVariable String email) {
        ResponseData<AdminStudentDTO> resp = new ResponseData<>();
        try {
            AdminStudentDTO dto = adminService.getStudentByEmail(email);
            resp.setStatus("success");
            resp.setData(dto);
        } catch (Exception e) {
            resp.setStatus("failed");
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}
