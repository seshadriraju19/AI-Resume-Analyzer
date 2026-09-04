import { useState } from "react";

function Register({ onRegisterSuccess, onShowLogin }) {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(
                "http://localhost:8080/api/auth/register",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        name,
                        email,
                        password,
                    }),
                }
            );

            const data = await response.text();

            if (response.ok) {
                setMessage("Registration successful. Please login.");

                setName("");
                setEmail("");
                setPassword("");

                setTimeout(() => {
                    onRegisterSuccess();
                }, 1000);

            } else {
                setMessage(data || "Registration failed");
            }

        } catch (error) {

            console.error(error);
            setMessage("Unable to connect to server");

        }
    };

    return (
        <div className="auth-container">

            <form className="auth-form" onSubmit={handleRegister}>

                <h2>Create Account</h2>

                <input
                    type="text"
                    placeholder="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                <button type="submit">
                    Register
                </button>

                {message && <p>{message}</p>}

                <p>
                    Already have an account?{" "}
                    <button
                        type="button"
                        onClick={onShowLogin}
                    >
                        Login
                    </button>
                </p>

            </form>

        </div>
    );
}

export default Register;