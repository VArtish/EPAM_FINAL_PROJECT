<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>


<fmt:message key="regexp.user.phone" var="phone_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.name" var="name_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.surname" var="surname_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.email" var="email_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.login" var="login_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.password" var="password_regexp" bundle="${regexp}"/>

<fmt:message key="page.reg.greeting" var="reg_greeting" bundle="${localization}"/>
<fmt:message key="page.basket.buy_button" var="buy" bundle="${localization}"/>
<fmt:message key="page.basket.purchases" var="purchases" bundle="${localization}"/>
<fmt:message key="page.basket.nubmder_goods" var="nubmer_goods" bundle="${localization}"/>
<fmt:message key="page.basket.total_price" var="total_price" bundle="${localization}"/>
<fmt:message key="page.basket.total" var="total" bundle="${localization}"/>
<fmt:message key="page.medicine_info.amount_in_package" var="amount_in_package" bundle="${localization}"/>
<fmt:message key="page.medicine_info.amount_in_plate" var="amount_in_plate" bundle="${localization}"/>
<fmt:message key="page.medicine_info.ingredients" var="medicine_ingredients" bundle="${localization}"/>
<fmt:message key="page.medicine_info.price" var="medicine_price" bundle="${localization}"/>
<fmt:message key="page.medicine_info.quantity" var="medicine_quantity" bundle="${localization}"/>
<fmt:message key="page.medicine_info.name" var="medicine_name" bundle="${localization}"/>
<fmt:message key="page.medicine_info.need_prescription" var="medicine_need_prescription" bundle="${localization}"/>
<fmt:message key="page.medicine_info.producing_country" var="medicine_producing_country" bundle="${localization}"/>
<fmt:message key="page.header.logout" var="logout" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet" var="user_cabinet" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_login_exist" var="login_exist" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_enter_new_password" var="enter_new_password" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_enter_old_password" var="enter_old_password" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_incorrect_new_password" var="incorrect_new_password_message" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_successful_change_password" var="successful_change_password_message" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_incorrect_old_password" var="incorrect_old_password_message" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_incorrect_repeat_new_password" var="incorrect_repeat_new_password_message" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_enter_new_password_again" var="enter_new_password_again" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_change_password_title" var="change_password_title" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_email_exist" var="email_exist" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_bookmarks" var="user_bookmarks" bundle="${localization}"/>
<fmt:message key="page.header.user_cabinet_update" var="update_user_cabinet" bundle="${localization}"/>
<fmt:message key="header.home" var="header_home" bundle="${localization}"/>
<fmt:message key="header.orders" var="header_orders" bundle="${localization}"/>
<fmt:message key="header.users" var="header_users" bundle="${localization}"/>
<fmt:message key="header.prescriptions" var="header_prescriptions" bundle="${localization}"/>
<fmt:message key="header.medicines" var="header_medicines" bundle="${localization}"/>
<fmt:message key="header.pharmacies" var="header_pharmacies" bundle="${localization}"/>
<fmt:message key="page.user_blocked.title" var="user_blocked_title" bundle="${localization}"/>
<fmt:message key="page.user_blocked_promo" var="user_blocked_promo" bundle="${localization}"/>
<fmt:message key="page.login.incorrect_sign_in" var="incorrect_sign_in" bundle="${localization}"/>
<fmt:message key="page.reg.title" var="reg_title" bundle="${localization}"/>
<fmt:message key="page.reg.promotion" var="reg_promotion" bundle="${localization}"/>
<fmt:message key="page.reg.terms_and_conditions" var="terms" bundle="${localization}"/>
<fmt:message key="page.reg.login_req" var="login_req" bundle="${localization}"/>
<fmt:message key="page.reg.pass_req" var="pass_req" bundle="${localization}"/>
<fmt:message key="page.reg.date_of_birth_req" var="date_of_birth_req" bundle="${localization}"/>
<fmt:message key="page.reg.passrepeat_req" var="passrep_req" bundle="${localization}"/>
<fmt:message key="page.reg.email_req" var="email_req" bundle="${localization}"/>
<fmt:message key="page.reg.name_req" var="name_req" bundle="${localization}"/>
<fmt:message key="page.reg.surname_req" var="surname_req" bundle="${localization}"/>
<fmt:message key="page.reg.terms_and_conditions_req" var="terms_req" bundle="${localization}"/>
<fmt:message key="user.login" var="login" bundle="${localization}"/>
<fmt:message key="user.password" var="pass" bundle="${localization}"/>
<fmt:message key="user.email" var="email" bundle="${localization}"/>
<fmt:message key="user.reppassword" var="pass_rep" bundle="${localization}"/>
<fmt:message key="user.create" var="create" bundle="${localization}"/>
<fmt:message key="user.name" var="name" bundle="${localization}"/>
<fmt:message key="user.date" var="date" bundle="${localization}"/>
<fmt:message key="user.surname" var="surname" bundle="${localization}"/>