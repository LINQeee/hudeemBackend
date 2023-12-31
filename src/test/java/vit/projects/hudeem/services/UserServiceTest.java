package vit.projects.hudeem.services;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//    @MockBean
//    private UserRepository userRepository;
//    @MockBean
//    private IpRepository ipRepository;
//    @MockBean
//    private UserMapper userMapper;
//    @MockBean
//    private IpMapper ipMapper;
//    @MockBean
//    private RecordMapper recordMapper;
//
//
//    @Test
//    void updateUserBio() {
////        userService = Mockito.spy(userService);
////        Mockito.doNothing().when(userService).checkIsUserAbleToLogin(any(UserDTO.class));
////
////        UserEntity toUpdate = new UserEntity();
////        toUpdate.setCurrentWeight(80);
////        toUpdate.setAge(33);
////
////        Mockito.when(userRepository.findByEmail("email"))
////                .thenReturn(Optional.of(toUpdate));
////
////        UserDTO newDto = new UserDTO();
////        newDto.setGender('M');
////        newDto.setHeight(175);
////        newDto.setAge(34);
////        newDto.setUsername("Kolya");
////        newDto.setEmail("email");
////
////        UserEntity result = new UserEntity();
////        result.setCurrentWeight(80);
////        result.setAge(34);
////        result.setGender('M');
////        result.setHeight(175);
////        result.setUsername("Kolya");
////        userService.updateUserBio(newDto);
////
////        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
////        Mockito.verify(userRepository).save(captor.capture());
////        UserEntity value = captor.getValue();
////        Assertions.assertThat(value).usingRecursiveComparison().isEqualTo(result);
//    }
//
//    @Test
//    void checkIsUserAbleToLogin_Positive_Result() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//        userDTO.setIp("ip");
//        userDTO.setPassword("123456");
//
//        IpEntity ip = new IpEntity();
//        ip.setIp("ip");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPasswordHash("123456");
//        userEntity.setExpireAuthorisationDate(LocalDate.now().plusDays(1));
//        userEntity.setIps(List.of(ip));
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//
//        assertDoesNotThrow(() -> userService.checkLoginAbilityWithPsw(userDTO));
//    }
//
//    @Test
//    void checkIsUserAbleToLogin_Wrong_Email_Or_Psw_Exception() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//        userDTO.setPassword("123456");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPasswordHash("123");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.empty());
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Неправильная почта или пароль");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Неправильная почта или пароль");
//    }
//
//    @Test
//    void checkIsUserAbleToLogin_Expired_Auth_Date_Exception() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//        userDTO.setPassword("123456");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPasswordHash("123456");
//        userEntity.setExpireAuthorisationDate(null);
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Срок авторизации истёк");
//
//        userEntity.setExpireAuthorisationDate(LocalDate.now().minusDays(1));
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Срок авторизации истёк");
//    }
//
//    @Test
//    void checkIsUserAbleToLogin_Unauthorised_Device_Exception() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//        userDTO.setIp("ip");
//        userDTO.setPassword("123456");
//
//        IpEntity ip = new IpEntity();
//        ip.setIp("wrongIp");
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPasswordHash("123456");
//        userEntity.setExpireAuthorisationDate(LocalDate.now().plusDays(1));
//        userEntity.setIps(null);
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Неавторизованное устройство");
//
//        userEntity.setIps(List.of(ip));
//
//        Assertions.assertThatThrownBy(() -> userService.checkLoginAbilityWithPsw(userDTO))
//                .isInstanceOf(AuthorizationException.class)
//                .hasMessage("Неавторизованное устройство");
//    }
//
//    @Test
//    void saveUser_Existing_User_Exception() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(new UserEntity()));
//
//        Assertions.assertThatThrownBy(() -> userService.saveUser(userDTO))
//                .isInstanceOf(ValidationException.class)
//                .hasMessage("Этот email занят")
//                .hasFieldOrPropertyWithValue("fieldType", InputFieldType.EMAIL);
//    }
//
//    @Test
//    void saveUser_Positive_Result() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("email");
//        userDTO.setId(20L);
//        userDTO.setIp("ip");
//
//        UserEntity toSave = new UserEntity();
//        toSave.setId(20L);
//        toSave.setEmail("email");
//
//        IpDTO ipDTO = new IpDTO(20L, "ip");
//
//        IpEntity ipEntity = new IpEntity();
//        ipEntity.setId(20L);
//        ipEntity.setIp("ip");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.empty());
//        Mockito.when(userMapper.toDTO(toSave)).thenReturn(userDTO);
//        Mockito.when(userMapper.fromDTO(userDTO)).thenReturn(toSave);
//        Mockito.when(userRepository.save(toSave)).thenReturn(toSave);
//        Mockito.when(ipMapper.fromDTO(ipDTO)).thenReturn(ipEntity);
//
//        Assertions.assertThat(userDTO).usingRecursiveComparison().isEqualTo(userService.saveUser(userDTO));
//
//        Mockito.verify(userRepository).save(toSave);
//        Mockito.verify(ipRepository).save(ipEntity);
//    }
//
//    @Test
//    void getUser() {
//        Long id = 10L;
//
//        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
//        Mockito.when(userRepository.findById(captor.capture())).thenReturn(Optional.of(new UserEntity()));
//        userService.getUser(id);
//
//        Assertions.assertThat(captor.getValue()).isEqualTo(id);
//    }
//
//    @Test
//    void getSummary() {
////        userService = Mockito.spy(userService);
////        Long id = 10L;
////        UserEntity userEntity = new UserEntity();
////        userEntity.setId(id);
////
////        RecordEntity recordEntity = new RecordEntity();
////        recordEntity.setUser(userEntity);
////        recordEntity.setId(id);
////        recordEntity.setCurrentWeight(10);
////        recordEntity.setDate(LocalDate.now());
////
////        RecordDTO recordDTO = new RecordDTO();
////        recordDTO.setId(id);
////        recordDTO.setCurrentWeight(10);
////        recordDTO.setDate(LocalDate.now());
////
////        userEntity.setRecords(List.of(recordEntity));
////
////        UserDTO userDTO = new UserDTO();
////        userDTO.setId(id);
////
////        IpEntity ipEntity = new IpEntity();
////        ipEntity.setIp("ip");
////
////        IpDTO ipDTO = new IpDTO(id, "ip");
////
////        userEntity.setIps(List.of(ipEntity));
////
////        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
////        Mockito.when(userMapper.toDTO(userEntity)).thenReturn(userDTO);
////        Mockito.when(recordMapper.toDTO(recordEntity)).thenReturn(recordDTO);
////        Mockito.when(ipMapper.toDTO(ipEntity)).thenReturn(ipDTO);
////
////        SummaryDTO summaryDTO = userService.getSummary(id);
////
////        recordDTO.setUserId(id);
////
////        Assertions.assertThat(summaryDTO.getIpDTOList()).usingRecursiveComparison().isEqualTo(List.of(ipDTO));
////        Assertions.assertThat(summaryDTO.getRecordDTOList()).usingRecursiveComparison().isEqualTo(List.of(recordDTO));
////        Assertions.assertThat(summaryDTO.getUserDTO()).usingRecursiveComparison().isEqualTo(userDTO);
//
//    }
}