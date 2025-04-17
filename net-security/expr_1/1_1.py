# 替换表（密文字母 → 明文字母）
substitution = {
    'K': 'e', #  
    'T': 't', # 
    'O': 'h', #
    'Q': 'a', #
    'P': 'o', #
    'E': 'r', #
    'M': 'n', 
    'C': 'd', 
    'H': 's', #
    'W': 'i', #
    'B': 'f', #
    'X': 'l', #
    'J': 'm', #
    'R': 'g', 
    'L': 'u', 
    'U': 'b', #
    'S': 'p', #
    'F': 'k',
    'D': 'y', #
    'Z': 'w', #
    'A': 'v', 
    'I': 'c'
}

# J -f

# 原始密文（保留原始空格和换行）
ciphertext = """
W T W H E Q T O K E B P E L H T P U K O K E K C K C W I Q T K C T P T O K R E K
Q T T Q H F E K J Q W M W M R U K B P E K L H T O Q T B E P J T O K H K O P M P
E K C C K Q C Z K T Q F K W M I E K Q H K C C K A P T W P M T P T O Q T I Q L H
K B P E Z O W I O T O K D R Q A K T O K X Q H T B L X X J K Q H L E K P B C K A
P T W P M T O Q T Z K O K E K O W R O X D E K H P X A K T O Q T T O K H K C K Q
C H O Q X X M P T O Q A K C W K C W M A Q W M T O Q T T O W H M Q T W P M L M C
K E R P C H O Q X X O Q A K Q M K Z U W E T O P B B E K K C P J Q M C T O Q T R
P A K E M J K M T P B T O K S K P S X K U D T O K S K P S X K B P E T O K S K P
S X K H O Q X X M P T S K E W H O B E P J T O K K Q E T O
"""

# 解密函数
def decrypt(ciphertext, substitution):
    decrypted = []
    for line in ciphertext.strip().split('\n'):
        decrypted_line = []
        for char in line.strip().split():
            decrypted_line.append(substitution.get(char, char))  # 保留未定义字符
        decrypted.append(' '.join(decrypted_line))
    return '\n'.join(decrypted)

# 执行解密
plaintext = decrypt(ciphertext, substitution)

# 打印结果（原始格式）
print("解密结果（保留原始格式）：")
print(plaintext)

# 打印结果（去除空格的可读版本）
print("\n可读版本（去除空格）：")
print(plaintext.replace(' ', '').replace('\n', '\n'))
with open('decrypted_text.txt', 'w') as f:
    f.write(plaintext.replace(' ', '').replace('\n', '\n'))