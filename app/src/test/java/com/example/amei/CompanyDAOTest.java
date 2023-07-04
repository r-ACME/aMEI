package com.example.amei;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amei.Modelos.Atividade;
import com.example.amei.Modelos.Company;
import com.example.amei.Modelos.QSA;
import com.example.amei.Negocios.Banco.CompanyDAO;
import com.example.amei.Negocios.Banco.DataBase;

public class CompanyDAOTest {

    @Mock
    private DataBase mockDataBase;

    private CompanyDAO companyDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        companyDAO = new CompanyDAO(mockDataBase);
    }

    @Test
    public void testGetAll_ReturnsListOfCompanies() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getReadableDatabase()).thenReturn(mockDatabase);

        Cursor mockCursor = mock(Cursor.class);
        when(mockDatabase.rawQuery(anyString(), any())).thenReturn(mockCursor);

        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.moveToNext()).thenReturn(false);

        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("id")))).thenReturn(1);
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("abertura")))).thenReturn("2022-01-01");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("cnpj")))).thenReturn("1234567890");
        // Configure other column values accordingly...

        List<Company> expectedCompanies = new ArrayList<>();
        Company expectedCompany = new Company();
        expectedCompany.setId(1);
        expectedCompany.setAbertura("2022-01-01");
        expectedCompany.setCnpj("1234567890");
        // Set other expected column values accordingly...
        expectedCompanies.add(expectedCompany);

        List<Company> companies = companyDAO.getAll();

        Assert.assertEquals(expectedCompanies.size(), companies.size());
        Assert.assertEquals(expectedCompanies.get(0).getId(), companies.get(0).getId());
        Assert.assertEquals(expectedCompanies.get(0).getAbertura(), companies.get(0).getAbertura());
        Assert.assertEquals(expectedCompanies.get(0).getCnpj(), companies.get(0).getCnpj());
        // Assert other column values accordingly...

        verify(mockDataBase).getReadableDatabase();
        verify(mockDatabase).rawQuery(anyString(), any());
        verify(mockCursor).moveToFirst();
        verify(mockCursor).moveToNext();
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("id")));
        verify(mockCursor).getString(eq(mockCursor.getColumnIndex("abertura")));
        verify(mockCursor).getString(eq(mockCursor.getColumnIndex("cnpj")));
        // Verify other column retrievals...
    }

    @Test
    public void testInsertQuerys_ReturnsListOfQueries() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getWritableDatabase()).thenReturn(mockDatabase);

        List<Company> companies = new ArrayList<>();
        Company company1 = new Company();
        company1.setId(1);
        company1.setAbertura("2022-01-01");
        company1.setCnpj("1234567890");
        // Set other company values accordingly...
        companies.add(company1);

        when(mockDataBase.fetchLastId(eq("company"))).thenReturn(10);

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO company VALUES(11, '2022-01-01', '1234567890')");
        // Add other expected queries...

        List<String> queries = companyDAO.insertQuerys(companies);

        Assert.assertEquals(expectedQueries.size(), queries.size());
        Assert.assertEquals(expectedQueries.get(0), queries.get(0));
        // Assert other expected queries...

        verify(mockDataBase).getWritableDatabase();
        verify(mockDataBase).fetchLastId(eq("company"));
    }

    @Test
    public void testSubQuerys_ReturnsListOfQueries() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.fetchLastId(eq("activity"))).thenReturn(1);
        when(mockDataBase.fetchLastId(eq("qsa"))).thenReturn(2);

        Company company = new Company();
        company.setId(1);

        List<Atividade> atividadesPrincipais = new ArrayList<>();
        Atividade atividadePrincipal = new Atividade();
        atividadePrincipal.setId(1);
        // Set other atividadePrincipal values accordingly...
        atividadesPrincipais.add(atividadePrincipal);
        company.setAtividade_principal((ArrayList<Atividade>) atividadesPrincipais);

        List<Atividade> atividadesSecundarias = new ArrayList<>();
        Atividade atividadeSecundaria = new Atividade();
        atividadeSecundaria.setId(2);
        // Set other atividadeSecundaria values accordingly...
        atividadesSecundarias.add(atividadeSecundaria);
        company.setAtividades_secundarias((ArrayList<Atividade>) atividadesSecundarias);

        List<QSA> qsaList = new ArrayList<>();
        QSA qsa = new QSA();
        qsa.setId(1);
        // Set other qsa values accordingly...
        qsaList.add(qsa);
        company.setQsa((ArrayList<QSA>) qsaList);

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO activity VALUES (1, '...', 'primary')");
        expectedQueries.add("INSERT INTO activity VALUES (2, '...', 'secondary')");
        expectedQueries.add("INSERT INTO qsa VALUES (2, '...')");
        expectedQueries.add("INSERT INTO company_qsa VALUES (1, 2)");

        List<String> queries = companyDAO.subQuerys(company);

        Assert.assertEquals(expectedQueries.size(), queries.size());
        Assert.assertEquals(expectedQueries.get(0), queries.get(0));
        Assert.assertEquals(expectedQueries.get(1), queries.get(1));
        Assert.assertEquals(expectedQueries.get(2), queries.get(2));
        Assert.assertEquals(expectedQueries.get(3), queries.get(3));

        verify(mockDataBase).fetchLastId(eq("activity"));
        verify(mockDataBase).fetchLastId(eq("qsa"));
    }

    @Test
    public void testIsMEI_ReturnsTrueIfCompanyIsMEI() {
        Company company = new Company();
        company.setNatureza_juridica("213-5");

        boolean isMEI = companyDAO.isMEI(company);

        Assert.assertTrue(isMEI);
    }

    @Test
    public void testIsMEI_ReturnsFalseIfCompanyIsNotMEI() {
        Company company = new Company();
        company.setNatureza_juridica("123-4");

        boolean isMEI = companyDAO.isMEI(company);

        Assert.assertFalse(isMEI);
    }
}
